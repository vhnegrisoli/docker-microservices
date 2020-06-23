import { Router } from "express";

import VendaController from "../controllers/VendaController";

const router = new Router();

router.post("/api/vendas/nova-venda", VendaController.salvarVenda);
router.put("/api/vendas/cancelar-venda/:id", VendaController.cancelarVenda);
router.get("/api/vendas", VendaController.buscarTodasAsVendas);
router.get("/api/vendas/:id", VendaController.buscarVendaPorId);
router.get(
  "/api/vendas/email/:email",
  VendaController.buscarVendaPorEmailUsuario
);
router.get("/api/vendas/nome/:email", VendaController.buscarPorNomeUsuario);

export default router;
