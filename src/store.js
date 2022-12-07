import { configureStore } from '@reduxjs/toolkit';
import {authReducer, bookReducer} from './slices';

export default configureStore({
  reducer: {
    book: bookReducer,
    auth: authReducer
  },
});