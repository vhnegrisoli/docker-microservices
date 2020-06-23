import mongoose from "mongoose";

const Schema = mongoose.Schema;
const model = mongoose.model;

const VendaSchema = new Schema({
  dataVenda: { type: Date, required: true },
  produtos: { type: Array, required: true },
  usuarioId: { type: String, required: true },
  usuarioNome: { type: String, required: true },
  usuarioEmail: { type: String, required: true },
  statusVenda: { type: String, required: true },
});

module.exports = model("Venda", VendaSchema);
