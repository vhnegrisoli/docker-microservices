import mongoose from 'mongoose';

const Schema = mongoose.Schema;
const model = mongoose.model;

const UsuarioSchema = new Schema({
  nome: Date,
  email: Object,
  senha: String,
  permissao: String,
});

module.exports = model('Usuario', UsuarioSchema);
