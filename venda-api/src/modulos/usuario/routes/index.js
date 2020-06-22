import { Router } from 'express';

import UsuarioController from '../controllers/UsuarioController';
import AuthController from '../controllers/AuthController';
import checkToken from '../../../config/auth/checkToken';

const routes = new Router();

routes.post('/api/auth/token', AuthController.auth);
routes.post('/api/usuarios', UsuarioController.salvarUsaurio);

routes.use(checkToken);

routes.post('/api/auth/check_token', AuthController.checkToken);

export default routes;
