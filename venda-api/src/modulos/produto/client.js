import axios from 'axios';
import * as url from '../../config/url/index';

export async function validarEstoque(produtoId, qtdDesejada) {
  let status = 0;
  let message = '';
  await axios
    .get(
      url.PRODUTO_API + `/api/produtos/validar-estoque?id=${produtoId}&qtdDesejada=${qtdDesejada}`,
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
