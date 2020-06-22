import express from 'express';
import cors from 'cors';
import axios from 'axios';

const server = express();
server.use(express.json());
server.use(cors());

server.get('/validar-estoque', async (req, res) => {
  const { produtoId, qtdDesejada } = req.query;
  console.log(req.query);
  await axios
    .get(
      `http://produto-api:8080/api/produtos/validar-estoque?id=${produtoId}&qtdDesejada=${qtdDesejada}`,
    )
    .then((data) => {
      console.log(data.data);
    })
    .catch((error) => {
      console.log(error.response.data.message);
    });
  return res.json({
    message: 'Teste',
  });
});

server.listen(3000);
