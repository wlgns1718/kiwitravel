import axios from "axios";

function apiInstance() {
  const instance = axios.create({
    baseURL: "http://localhost:9999",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { apiInstance };
