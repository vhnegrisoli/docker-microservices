import mongoose from 'mongoose';

const Schema = mongoose.Schema;
const model = mongoose.model;

const VendaSchema = new Schema({
  dataVenda: Date,
  produtos: Object,
  usuarioId: String,
  usuarioNome: String,
  usuarioEmail: String,
});

module.exports = model('Venda', VendaSchema);
