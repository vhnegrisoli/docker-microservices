module.exports = {
  openapi: '3.0.1',
  info: {
    version: '1.3.0',
    title: 'Venda-API',
    description: 'Back-end do microsserviço Venda-API',
  },
  servers: [
    {
      url: 'http: //localhost:8081/',
      description: 'Local server',
    },
  ],
  security: [
    {
      ApiKeyAuth: [],
    },
  ],
  tags: [
    {
      name: 'Usuários',
    },
    {
      name: 'Vendas',
    },
  ],
  paths: {
    '/api/usuarios': {
      get: {
        tags: ['Usuários'],
        description: 'Buscar todos os usuários',
        operationId: 'buscarTodosUsuarios',
        responses: {
          '200': {
            description: 'Recupera todos os usuários',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/schemas/Usuarios',
                },
              },
            },
          },
          '400': {
            description: 'Erro ao buscar usuários',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/Error',
                },
                example: {
                  message: 'Error message',
                  status: 400,
                },
              },
            },
          },
        },
      },
      post: {
        tags: ['Usuários'],
        description: 'Create Usuarios',
        operationId: 'createUsers',
        parameters: [],
        requestBody: {
          content: {
            'application/json': {
              schema: {
                $ref: '#/components/schemas/Usuarios',
              },
            },
          },
          required: true,
        },
        responses: {
          '200': {
            description: 'New Usuarios were created',
          },
          '400': {
            description: 'Invalid parameters',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/Error',
                },
                example: {
                  message: 'Usuario identificationNumbers 10, 20 already exist',
                  internal_code: 'invalid_parameters',
                },
              },
            },
          },
        },
      },
    },
    '/api/vendas': {
      get: {
        tags: ['Vendas'],
        description: 'Buscar todas as vendas',
        operationId: 'buscarTodasAsVendas',
        responses: {
          '200': {
            description: 'Recupera todas as vendas',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/schemas/Venda',
                },
              },
            },
          },
          '400': {
            description: 'Erro ao buscar vendas',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/Error',
                },
                example: {
                  message: 'Error message',
                  status: 400,
                },
              },
            },
          },
        },
      },
    },
    '/api/vendas/nova': {
      post: {
        tags: ['Vendas'],
        description: 'Buscar todas as vendas',
        operationId: 'buscarTodasAsVendas',
        responses: {
          '200': {
            description: 'Recupera todas as vendas',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/schemas/Venda',
                },
              },
            },
          },
          '400': {
            description: 'Erro ao buscar vendas',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/Error',
                },
                example: {
                  message: 'Error message',
                  status: 400,
                },
              },
            },
          },
        },
      },
    },
    '/api/vendas/cancelar/:id': {
      get: {
        tags: ['Vendas'],
        description: 'Buscar todas as vendas',
        operationId: 'buscarTodasAsVendas',
        responses: {
          '200': {
            description: 'Recupera todas as vendas',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/schemas/Venda',
                },
              },
            },
          },
          '400': {
            description: 'Erro ao buscar vendas',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/Error',
                },
                example: {
                  message: 'Error message',
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
        type: 'object',
        properties: {
          _id: {
            type: 'string',
            description: 'ID do usuário',
            example: '5099803df3f4948bd2f98391',
          },
          nome: {
            type: 'string',
            example: 'João',
          },
          email: {
            type: 'string',
            example: 'joao@gmail.com',
          },
          senha: {
            type: 'string',
            example: '123456',
          },
        },
      },
      Venda: {
        type: 'object',
        properties: {
          _id: {
            type: 'string',
            description: 'ID da venda',
            example: '5099803df3f4948bd2f98391',
          },
          produtos: {
            type: 'array',
            items: {
              $ref: '#/components/schemas/Produtos',
            },
          },
          statusVenda: {
            type: 'enum',
            properties: ['REALIZADA', 'CANCELADA'],
            example: 'REALIZADA',
          },
          usuarioId: {
            type: 'string',
            example: '5099803df3f4948bd2f98391',
          },
          usuarioNome: {
            type: 'string',
            example: 'João',
          },
          usuarioEmail: {
            type: 'string',
            example: 'joao@gmail.com',
          },
        },
      },
      Produtos: {
        type: 'object',
        properties: {
          produtoId: {
            type: 'integer',
            description: 'ID do produto',
            example: 1,
          },
          qtdDesejada: {
            type: 'integer',
            description: 'Quantidade desejada',
            example: 1,
          },
        },
      },
      ProdutoResponse: {
        
      },
      Usuarios: {
        type: 'array',
        items: {
          $ref: '#/components/schemas/Usuario',
        },
      },
    },
    Error: {
      type: 'object',
      properties: {
        message: {
          type: 'string',
        },
        internal_code: {
          type: 'string',
        },
      },
    },
    securitySchemes: {
      ApiKeyAuth: {
        type: 'bearer token',
        in: 'header',
        name: 'Authorization',
      },
    },
  },
};
