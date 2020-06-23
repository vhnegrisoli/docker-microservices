import axios from 'axios';
import * as url from '../../config/url/index';

class ProdutoClient {
  async validarEstoque(produtos, token) {
    let status = 0;
    let message = '';
    let headers = {
      headers: {
        authorization: token,
      },
    };
    await axios
      .put(
        url.PRODUTO_API + '/api/produtos/validar-estoque-varios',
        {
          produtos,
        },
        headers,
      )
      .then((data) => {
        status = data.status;
        message = 'Produto em estoque!';
      })
      .catch((error) => {
        status = error.response.data.status;
        message = error.response.data.message;
      });
    return {
      status,
      message,
    };
  }

  async buscarPedidoCompleto(produtos, token) {
    let pedido = [];
    let headers = {
      headers: {
        authorization: token,
      },
    };
    await axios
      .post(
        url.PRODUTO_API + '/api/produtos/informacoes-pedido',
        {
          produtos,
        },
        headers,
      )
      .then((data) => {
        pedido = data.data;
      })
      .catch((error) => {});
    return pedido;
  }
}
export default new ProdutoClient();
