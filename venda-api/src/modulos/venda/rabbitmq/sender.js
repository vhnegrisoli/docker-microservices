import amqp from 'amqplib/callback_api';

import * as url from '../../../config/url/index';

const queue = 'produto-atualizar-estoque.queue';
const exchange = 'produto.topic';

export function sendMessage(produtos) {
  amqp.connect(url.RABBIT_MQ_URL, function (error0, connection) {
    if (error0) {
      throw error0;
    }
    connection.createChannel(function (error1, channel) {
      if (error1) {
        throw error1;
      }

      channel.assertQueue(queue, {
        durable: true,
      });

      channel.assertExchange(exchange, 'topic', {
        durable: true,
      });
      produtos.forEach((produto) => {
        const produtoJsonString = JSON.stringify(produto);
        console.log(produtoJsonString);
        channel.sendToQueue(queue, Buffer.from(produtoJsonString));
      });
    });
    setTimeout(function () {
      connection.close();
    }, 500);
  });
}
