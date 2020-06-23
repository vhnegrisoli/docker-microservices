import express, { Router } from "express";
import cors from "cors";
import swaggerJsDoc from "swagger-jsdoc";
import swaggerUi from "swagger-ui-express";
import vendaRoutes from "./src/modulos/venda/routes/index";
import usuarioRoutes from "./src/modulos/usuario/routes/index";
import * as db from "./src/config/db/config";

import checkToken from "./src/config/auth/checkToken";

const app = express();

db.connect();
app.use(express.json());
app.use(cors());

const swaggerOptions = {
  swaggerDefinition: {
    info: {
      version: "1.0.0",
      title: "Venda-API",
      description: "Back-end do microsserviço de vendas",
      contact: {
        name: "Venda-API",
      },
      servers: ["http://localhost:3000"],
    },
  },
  apis: ["../../app.js"],
};

const swaggerDocs = swaggerJsDoc(swaggerOptions);

const swaggerRouter = new Router();

swaggerRouter.use("/api-docs", swaggerUi.serve);
swaggerRouter.get("/api-docs", swaggerUi.setup(swaggerDocs));

app.use(swaggerRouter);
app.use(usuarioRoutes);
app.use(checkToken);
app.use(vendaRoutes);

app.listen(3000);
