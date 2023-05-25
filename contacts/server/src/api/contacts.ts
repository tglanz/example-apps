import { Router, Request, Response } from "express";
import { PrismaClient } from "@prisma/client";

const router = Router({
  caseSensitive: false,
});

export interface ContactInfo {
    name: string
}

router.get("/api/contacts", async (req: Request, res: Response) => {
  const prisma = new PrismaClient();
  const contacts = await prisma.contacts.findMany();
  res
    .status(200)
    .send({ contacts });
});

router.get("/api/contacts/:contactId", async (req: Request, res: Response) => {
  const prisma = new PrismaClient();
  const contact = await prisma.contacts.findUniqueOrThrow({
    where: { id: +req.params.contactId }
  });

  res
    .status(200)
    .send({ contact });
});

router.put("/api/contacts", async (req: Request, res: Response) => {
  const contact: ContactInfo = req.body;

  const prisma = new PrismaClient();
  const result = await prisma.contacts.create({
    data: {
      ...contact
    }
  });

  res.status(200).send({ contact: result });
});

router.post("/api/contacts/:contactId", async (req: Request, res: Response) => {
  const prisma = new PrismaClient();

  const result = await prisma.contacts.update({
    where: { id: +req.params.contactId },
    data: req.body
  });

  res
    .status(200)
    .send(result);
});

export default router;
