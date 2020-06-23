import Venda from '../model/Venda';
import ProdutoClient from '../../produto/ProdutoClient';
import * as mq from '../rabbitmq/sender';

class VendaController {
  async salvarVenda(req, res) {
    try {
      const { authUser } = req;
      const { produtos } = req.body;
      const { authorization } = req.headers;
      const response = await ProdutoClient.validarEstoque(produtos, authorization);
      if (response && response.status >= 400) {
        return res.status(response.status).json({ message: response });
      }
      const vendaSalva = await Venda.create({
        usuarioId: authUser.id,
        usuarioNome: authUser.nome,
        usuarioEmail: authUser.email,
        produtos,
        statusVenda: 'REALIZADA',
        dataVenda: new Date(),
      });
      const produtosEstoque = [];
      produtos.forEach((produto) => {
        produtosEstoque.push({
          produtoId: produto.produtoId,
          qtdDesejada: produto.qtdDesejada,
          reduzirEstoque: true,
        });
      });
      mq.sendMessage(produtosEstoque);
      return res.json(vendaSalva);
    } catch (error) {
      return res.status(400).json({ message: 'Não foi possível realizar a venda' });
    }
  }

  async cancelarVenda(req, res) {
    const { id } = req.params;
    try {
      const venda = await Venda.findById(id);
      if (!venda) {
        return res.status(400).json({ message: 'A venda não foi encontrada' });
      }
      venda.statusVenda = 'CANCELADA';
      await venda.save();
      const produtosEstoque = [];
      const produtos = venda.produtos;
      produtos.forEach((produto) => {
        produtosEstoque.push({
          produtoId: produto.produtoId,
          qtdDesejada: produto.qtdDesejada,
          reduzirEstoque: false,
        });
      });
      mq.sendMessage(produtosEstoque);
      const vendaCancelada = await Venda.findById(id);
      return res.json(vendaCancelada);
    } catch (error) {
      return res.status(400).json({ message: 'Não foi possível cancelar a venda' });
    }
  }
  async buscarTodasAsVendas(req, res) {
    try {
      const vendas = await Venda.find();
      return res.json(vendas);
    } catch (error) {
      return res.status(400).json({ message: 'Erro ao buscar as vendas.' });
    }
  }
  async buscarVendaPorId(req, res) {
    const { id } = req.params;
    try {
      const venda = await Venda.findById(id);
      if (!venda) {
        return res.status(400).json({ message: 'A venda não foi encontrada.' });
      }
      return res.json(venda);
    } catch (error) {
      return res.status(400).json({ message: 'Erro ao buscar venda.' });
    }
  }
  async buscarVendaPorEmailUsuario(req, res) {
    const { email } = req.params;
    try {
      const vendas = await Venda.find({ usuarioEmail: email });
      if (!vendas) {
        return res.status(400).json({ message: 'As vendas não foram encontradas.' });
      }
      return res.json(vendas);
    } catch (error) {
      return res.status(400).json({ message: 'Erro ao buscar vendas.' });
    }
  }
  async buscarPorNomeUsuario(req, res) {
    const { nome } = req.params;
    try {
      const vendas = await Venda.find({ usuarioNome: nome });
      if (!vendas) {
        return res.status(400).json({ message: 'As vendas não foram encontradas.' });
      }
      return res.json(vendas);
    } catch (error) {
      return res.status(400).json({ message: 'Erro ao buscar vendas.' });
    }
  }
}
export default new VendaController();
