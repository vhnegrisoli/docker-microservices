import { Router } from "express";

import UsuarioController from "../controllers/UsuarioController";
import AuthController from "../controllers/AuthController";
import checkToken from "../../../config/auth/checkToken";

const router = new Router();

router.post("/auth/token", AuthController.auth);
router.post("/auth/check_token", AuthController.checkToken);
router.post("/api/usuarios", UsuarioController.salvarUsuario);

router.use(checkToken);

router.put("/api/usuarios/:id", UsuarioController.editarUsuario);
router.get("/api/usuarios", UsuarioController.buscarTodosUsuarios);
router.get("/api/usuarios/:id", UsuarioController.buscarUsuarioPorId);

export default router;
