import { Router } from 'express';

import UsuarioController from '../controllers/UsuarioController';
import checkToken from '../../../config/auth/checkToken';

const routes = new Router();

routes.post('/api/usuarios', UsuarioController.save);
routes.post('/api/auth/token', AuthController.auth);
routes.post('/api/auth/check_token', AuthController.checkToken);

routes.use(checkToken);

routes.get('/api/usuarios', UsuarioController.findAll);
routes.get('/api/usuarios/:id', UsuarioController.findById);
routes.put('/api/usuarios/:id', UsuarioController.update);
routes.delete('/api/usuarios/:id', UsuarioController.delete);
routes.get('/api/usuarios/email/:email', UsuarioController.findByEmail);

export default routes;
