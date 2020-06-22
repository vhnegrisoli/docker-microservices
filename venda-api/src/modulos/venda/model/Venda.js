import mongoose from "mongoose";

const Schema = mongoose.Schema;
const model = mongoose.model;

const VendaSchema = new Schema({
  dataVenda: { type: Date, required: true },
  produtos: { type: Array, required: true },
  usuarioId: { type: String, required: true },
  usuarioNome: { type: String, required: true },
  usuarioEmail: { type: String, required: true },
});
VendaSchema.pre("save", async function (next) {
  dataVenda = new Date();
  return next();
});

module.exports = model("Venda", VendaSchema);
