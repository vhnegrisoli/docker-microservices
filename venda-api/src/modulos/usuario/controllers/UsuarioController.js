import jwt from 'jsonwebtoken';
import bcrypt from 'bcryptjs';
import * as auth from '../../../config/auth/authKey';

import Usuario from '../model/Usuario';

class UsuarioController {
  async salvarUsaurio(req, res) {
    try {
      const { nome, email, senha } = req.body;
      this.validarEmailExistente(email, res);
      const usuario = await Usuario.create({
        nome,
        email,
        senha,
      });
      return res.json(usuario);
    } catch (error) {
      return res.status(400).json(error);
    }
  }

  async validarEmailExistente(email, res) {
    const usuario = Usuario.find({ email });
    if (usuario) {
      return res.status(400).json({ message: 'Usuário já existente.' });
    }
  }
}
export default new UsuarioController();
