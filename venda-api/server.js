import express from "express";
import cors from "cors";

const server = express();
server.use(express.json());
server.use(cors());

server.get("/teste", (req, res) => {
  return res.json({
    message: "Teste",
  });
});

server.listen(3000);
