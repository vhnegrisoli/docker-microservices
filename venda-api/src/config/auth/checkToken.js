import jwt from 'jsonwebtoken';
import { promisify } from 'util';

import * as auth from '../auth/authKey';

const bearer = 'bearer ';

export default async (req, res, next) => {
  const { authorization } = req.headers;
  const unauthorized = { message: 'Usuário não autenticado.' };
  if (!authorization || !authorization.toLowerCase().includes(bearer)) {
    return res.status(401).json(unauthorized);
  }
  const token = authorization.split(' ')[1];
  try {
    const decoded = await promisify(jwt.verify)(token, auth.apiKey);
    req.authUser = decoded.authUser;
    return next();
  } catch (error) {
    return res.status(401).message(unauthorized);
  }
};
