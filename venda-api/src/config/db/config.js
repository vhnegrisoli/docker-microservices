import mongoose from 'mongoose';

import * as url from '../url/index';

export function connect() {
  mongoose.connect(url.MONGO_DB_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });
  mongoose.connection.on('connected', function () {
    console.info('Conecatdo ao MongoDB');
  });
  mongoose.connection.on('error', function () {
    console.info('Erro ao conectar ao MongoDB');
  });
}
