import { createAsyncThunk } from "@reduxjs/toolkit";
import { createSlice } from "@reduxjs/toolkit";

const getLocalUser = () => {
  const localUser = window.localStorage.getItem("user");
  if (localUser) {
    return { user: JSON.parse(localUser), status: "logged" };
  }
  return { user: {}, status: "none" };
};

const initalValue = getLocalUser();

const login = createAsyncThunk(
  "auth/login",
  async (input, { rejectWithValue }) => {
    const users = window.localStorage.getItem("userList");
    const currentUser = JSON.parse(users).find(
      (user) => user.email === input.email && user.password === input.password
    );
    if (currentUser) {
      const userData = { name: currentUser.name, admin: currentUser.admin };
      window.localStorage.setItem("user", JSON.stringify(userData));
      return userData;
    } else return rejectWithValue("USER_NOT_FOUND");
  }
);

export const authSlice = createSlice({
  name: "auth",
  initialState: initalValue,
  reducers: {
    register: (state, { payload }) => {
      const users = window.localStorage.getItem("userList");
      if (users) {
        const usersArr = JSON.parse(users);
        usersArr.push(payload);
        window.localStorage.setItem("userList", JSON.stringify(usersArr));
      } else {
        window.localStorage.setItem(
          "userList",
          JSON.stringify([{ ...payload }])
        );
      }
    },
    logout: (state) => {
      state.user = {};
      state.status = "none";
      window.localStorage.removeItem("user");
    },
    clearStatus: (state) => {
      state.status = "none";
    },
  },
  extraReducers: (builder) => {
    builder.addCase(login.fulfilled, (state, action) => {
      state.user = action.payload;
      state.status = "logged";
    });
    builder.addCase(login.rejected, (state, action) => {
      state.status = "denied";
    });
  },
});

export const { register, logout, clearStatus } = authSlice.actions;
export const authReducer = authSlice.reducer;
export { login };
