import express from 'express';
import cors from 'cors';
import routes from './src/modulos/venda/routes/index';
import * as db from './src/config/db/config';

const app = express();

app.use(express.json());
app.use(cors());
app.use(routes);
db.connect();

app.listen(3000);
