import Usuario from "../model/Usuario";

class UsuarioController {
  async salvarUsuario(req, res) {
    try {
      const { email } = req.body;
      const usuarioExistente = Usuario.findOne({ email });
      if (usuarioExistente) {
        return res.status(400).json({ message: "Usuário já existente." });
      }
      const usuario = await Usuario.create(req.body);
      return res.json(usuario);
    } catch (error) {
      return res.status(400).json(error);
    }
  }

  async editarUsuario(req, res) {
    try {
      const { id } = req.params;
      const { email } = req.body;
      const _id = id.toString();
      const usuarioExistente = await Usuario.findOne({ email });
      if (usuarioExistente && String(usuarioExistente._id) !== String(_id)) {
        return res.status(400).json({ message: "Usuário já existente." });
      }
      const usuario = await Usuario.create(req.body);
      return res.json(usuario);
    } catch (error) {
      return res.status(400).json(error);
    }
  }

  async buscarTodosUsuarios(req, res) {
    try {
      const usuarios = await Usuario.find();
      return res.json(usuarios);
    } catch (error) {
      return res.status(400).json(error);
    }
  }

  async buscarUsuarioPorId(req, res) {
    try {
      const { id } = req.params;
      const usuario = await Usuario.findById(id);
      if (!usuario) {
        res.status(400).json({ message: "O usuário não foi encontrado." });
      }
      return res.json(usuario);
    } catch (error) {
      return res.status(400).json({ message: "O usuário não foi encontrado." });
    }
  }
}
export default new UsuarioController();
