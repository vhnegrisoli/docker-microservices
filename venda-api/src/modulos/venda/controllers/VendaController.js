import Venda from "../model/Venda";
import ProdutoClient from "../../produto/ProdutoClient";

class VendaController {
  async salvarVenda(req, res) {
    try {
      const { authUser } = req;
      const { produtos } = req.body;
      const { authorization } = req.headers;
      const response = await ProdutoClient.validarEstoque(
        produtos,
        authorization
      );
      if (response && response.status >= 400) {
        return res.status(response.status).json({ message: response });
      }
      const vendaSalva = await Venda.create({
        usuarioId: authUser.id,
        usuarioNome: authUser.id,
        usuarioEmail: authUser.id,
        produtos,
        statusVenda: "REALIZADA",
        dataVenda: new Date(),
      });
      return res.json(vendaSalva);
    } catch (error) {
      console.info(error);
      return res
        .status(400)
        .json({ message: "Não foi possível realizar a venda" });
    }
  }

  async cancelarVenda(req, res) {}
  async buscarTodasAsVendas(req, res) {}
  async buscarVendaPorId(req, res) {}
  async buscarVendaPorEmailUsuario(req, res) {}
  async buscarPorNomeUsuario(req, res) {}
}
export default new VendaController();
