import { Router, Request, Response } from "express";

const router = Router({
  caseSensitive: false,
});

router.get("/api/status", async function(_req: Request, res: Response) {
  res
    .status(200)
    .send({ message: "?ok" })
});

export default router;
