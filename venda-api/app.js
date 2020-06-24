import express, { Router } from "express";
import cors from "cors";
import swaggerUi from "swagger-ui-express";
import vendaRoutes from "./src/modulos/venda/routes/index";
import usuarioRoutes from "./src/modulos/usuario/routes/index";
import * as db from "./src/config/db/config";

import checkToken from "./src/config/auth/checkToken";
import apiDocumentation from "./docs/apiDocumentation";

const app = express();

db.connect();
app.use(express.json());
app.use(cors());

app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(apiDocumentation));
app.use(usuarioRoutes);
app.use(checkToken);
app.use(vendaRoutes);

app.listen(3000);
