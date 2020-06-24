module.exports = {
  openapi: "3.0.1",
  info: {
    version: "1.3.0",
    title: "Venda-API",
    description: "Back-end do microsserviço Venda-API",
  },
  servers: [
    {
      url: "http: //localhost:8081/",
      description: "Local server",
    },
  ],
  security: [
    {
      ApiKeyAuth: [],
    },
  ],
  tags: [
    {
      name: "Autenticação",
    },
    {
      name: "Usuários",
    },
    {
      name: "Vendas",
    },
  ],
  paths: {
    "/auth/token": {
      post: {
        tags: ["Autenticação"],
        description: "Autenticar usuários e devolver uma token JWT",
        operationId: "auth",
        requestBody: {
          content: {
            "application/json": {
              schema: {
                $ref: "#/components/schemas/AuthRequest",
              },
            },
          },
        },
        responses: {
          "200": {
            description: "Autenticar usuários e devolver uma token JWT",
            content: {
              "text/plain": {
                schema: {
                  $ref: "#/components/schemas/AccessToken",
                },
              },
            },
          },
          "400": {
            description: "Erro ao buscar usuários",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/Error",
                },
                example: {
                  message: "Error message",
                  status: 400,
                },
              },
            },
          },
        },
      },
    },
    "/auth/check_token": {
      post: {
        tags: ["Autenticação"],
        description: "Verificar se o token de acesso é válido",
        operationId: "checkToken",
        parameters: [
          {
            in: "query",
            name: "token",
            description: "Token de acesso",
            example:
              "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVXNlciI6eyJpZCI6IjVlZjJhYjg1NzEwODdhNDA5Y2E2NDA4OCIsImVtYWlsIjoidGVzdGVAZW1haWwuY29tIiwibm9tZSI6InRlc3RlIn0sImlhdCI6MTU5Mjk2MTk1MSwiZXhwIjoxNTkzMDQ4MzUxfQ.qQRi8z4HND78JDD8ApLVB6forT23moMGCR6VPsL0BQo",
            required: true,
          },
        ],
        responses: {
          "200": {
            description: "Validar se uma token é válida.",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/schemas/TokenAccepted",
                },
              },
            },
          },
          "400": {
            description: "Invalid parameters",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/Error",
                },
                example: {
                  message: "Token inválida",
                  internal_code: "invalid_parameters",
                },
              },
            },
          },
        },
      },
    },
    "/api/usuarios": {
      get: {
        tags: ["Usuários"],
        description: "Buscar todos os usuários",
        operationId: "buscarTodosUsuarios",
        responses: {
          "200": {
            description: "Recupera todos os usuários",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/schemas/Usuarios",
                },
              },
            },
          },
          "400": {
            description: "Erro ao buscar usuários",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/Error",
                },
                example: {
                  message: "Error message",
                  status: 400,
                },
              },
            },
          },
        },
      },
      post: {
        tags: ["Usuários"],
        description: "Salvar novo usuário",
        operationId: "salvarUsuario",
        parameters: [],
        requestBody: {
          content: {
            "application/json": {
              schema: {
                $ref: "#/components/schemas/Usuarios",
              },
            },
          },
          required: true,
        },
        responses: {
          "200": {
            description: "Recupera todos os usuários",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/schemas/Usuarios",
                },
              },
            },
          },
          "400": {
            description: "Invalid parameters",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/Error",
                },
                example: {
                  message: "Erro ao salvar usuário",
                  status: 400,
                },
              },
            },
          },
        },
      },
    },
    "/api/vendas": {
      get: {
        tags: ["Vendas"],
        description: "Buscar todas as vendas",
        operationId: "buscarTodasAsVendas",
        responses: {
          "200": {
            description: "Recupera todas as vendas",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/schemas/Venda",
                },
              },
            },
          },
          "400": {
            description: "Erro ao buscar vendas",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/Error",
                },
                example: {
                  message: "Error message",
                  status: 400,
                },
              },
            },
          },
        },
      },
    },
    "/api/vendas/nova": {
      post: {
        tags: ["Vendas"],
        description: "Salvar nova venda",
        operationId: "salvarVenda",
        responses: {
          "200": {
            description: "Salva uma nova venda",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/schemas/Venda",
                },
              },
            },
          },
          "400": {
            description: "Erro ao buscar vendas",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/Error",
                },
                example: {
                  message: "Error message",
                  status: 400,
                },
              },
            },
          },
        },
      },
    },
    "/api/vendas/cancelar/:id": {
      get: {
        tags: ["Vendas"],
        description: "Buscar todas as vendas",
        operationId: "buscarTodasAsVendas",
        responses: {
          "200": {
            description: "Recupera todas as vendas",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/schemas/Venda",
                },
              },
            },
          },
          "400": {
            description: "Erro ao buscar vendas",
            content: {
              "application/json": {
                schema: {
                  $ref: "#/components/Error",
                },
                example: {
                  message: "Error message",
                  status: 400,
                },
              },
            },
          },
        },
      },
    },
  },
  components: {
    schemas: {
      Usuario: {
        type: "object",
        properties: {
          _id: {
            type: "string",
            description: "ID do usuário",
            example: "5099803df3f4948bd2f98391",
          },
          nome: {
            type: "string",
            example: "João",
          },
          email: {
            type: "string",
            example: "joao@gmail.com",
          },
          senha: {
            type: "string",
            example: "123456",
          },
        },
      },
      Venda: {
        type: "object",
        properties: {
          _id: {
            type: "string",
            description: "ID da venda",
            example: "5099803df3f4948bd2f98391",
          },
          produtos: {
            type: "array",
            items: {
              $ref: "#/components/schemas/Produtos",
            },
          },
          statusVenda: {
            type: "enum",
            properties: ["REALIZADA", "CANCELADA"],
            example: "REALIZADA",
          },
          usuarioId: {
            type: "string",
            example: "5099803df3f4948bd2f98391",
          },
          usuarioNome: {
            type: "string",
            example: "João",
          },
          usuarioEmail: {
            type: "string",
            example: "joao@gmail.com",
          },
        },
      },
      Produtos: {
        type: "object",
        properties: {
          produtoId: {
            type: "integer",
            description: "ID do produto",
            example: 1,
          },
          qtdDesejada: {
            type: "integer",
            description: "Quantidade desejada",
            example: 1,
          },
        },
      },
      ProdutoResponse: {},
      AuthRequest: {
        type: "object",
        properties: {
          email: {
            type: "string",
            description: "Email do usuário",
          },
          senha: {
            type: "string",
            description: "Senha do usuário",
          },
        },
      },
      AccessToken: {
        type: "object",
        example:
          "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVXNlciI6eyJpZCI6IjVlZjJhYjg1NzEwODdhNDA5Y2E2NDA4OCIsImVtYWlsIjoidGVzdGVAZW1haWwuY29tIiwibm9tZSI6InRlc3RlIn0sImlhdCI6MTU5Mjk2MTk1MSwiZXhwIjoxNTkzMDQ4MzUxfQ.qQRi8z4HND78JDD8ApLVB6forT23moMGCR6VPsL0BQo",
      },
      TokenAccepted: {
        type: "object",
        properties: {
          message: {
            type: "string",
            description: "Token autenticada.",
            example: "Token autenticada.",
          },
        },
      },
      Usuarios: {
        type: "array",
        items: {
          $ref: "#/components/schemas/Usuario",
        },
      },
    },
    Error: {
      type: "object",
      properties: {
        message: {
          type: "string",
        },
        status: {
          type: "integer",
        },
      },
    },
    securitySchemes: {
      ApiKeyAuth: {
        type: "bearer token",
        in: "header",
        name: "Authorization",
      },
    },
  },
};
