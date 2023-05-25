import express from "express";
import dotenv from "dotenv";
import bodyParser from "body-parser";

import statusApi from "./api/status";
import contactsApi from "./api/contacts";

dotenv.config();

const app = express();
const port = process.env.PORT || 5000;

app.use(bodyParser.urlencoded({
  extended: true,
}));

app.use(bodyParser.json());

app.use(statusApi);
app.use(contactsApi);

app.listen(port, () => {
  console.log(`Server is running and listening over port: ${port}`);
});
