import { Router } from "express";

import * as produtoClient from "../../produto/ProdutoClient";

const router = new Router();

/**
 * @swagger
 */
router.get("/validar-estoque", async (req, res) => {
  const { produtoId, qtdDesejada } = req.query;
  const response = await produtoClient.validarEstoque(produtoId, qtdDesejada);
  if (response.status >= 400) {
    return res.status(response.status).json({ message: response.message });
  }
  return res.json({ message: "Ok" });
});

export default router;
