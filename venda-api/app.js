import express from 'express';
import cors from 'cors';
import vendaRoutes from './src/modulos/venda/routes/index';
import usuarioRoutes from './src/modulos/usuario/routes/index';
import * as db from './src/config/db/config';

const app = express();

app.use(express.json());
app.use(cors());
app.use(vendaRoutes);
app.use(usuarioRoutes);
db.connect();

app.listen(3000);
