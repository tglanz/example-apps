import { Router, Request, Response } from "express";
import multer from "multer";

const router = Router();

const fileStorage = multer.diskStorage({
  destination: "uploads"
});

const upload = multer({
  storage: fileStorage
});

router.post("/api/file", upload, async (req: Request, res: Response) => {
  const file = req.file;
  console.log(file);
});
