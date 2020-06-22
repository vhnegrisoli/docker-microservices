import { Router } from 'express';
import swaggerJsDoc from 'swagger-jsdoc';
import swaggerUi from 'swagger-ui-express';
import * as produtoClient from '../../produto/ProdutoClient';


const router = new Router();
const swaggerOptions = {
  swaggerDefinition: {
    info: {
      version: '1.0.0',
      title: 'Venda-API',
      description: 'Back-end do microsserviço de vendas',
      contact: {
        name: 'Venda-API',
      },
      servers: ['http://localhost:3000'],
    },
  },
  apis: ['../../app.js'],
};

const swaggerDocs = swaggerJsDoc(swaggerOptions);
router.use('/api-docs', swaggerUi.serve);
router.get('/api-docs', swaggerUi.setup(swaggerDocs));

/**
 * @swagger
 * /validar-estoque:
 *  get:
 *    description: Use to request all customers
 *    responses:
 *      '200':
 *        description: A successful response
 */
router.get('/validar-estoque', async (req, res) => {
  const { produtoId, qtdDesejada } = req.query;
  const response = await produtoClient.validarEstoque(produtoId, qtdDesejada);
  if (response.status >= 400) {
    return res.status(response.status).json({ message: response.message });
  }
  return res.json({ message: 'Ok' });
});

export default router;
