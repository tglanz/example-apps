import { FormEvent, useState } from "react"
import { useCreateContact, useUpdateContact } from "../../services/api";
import { useMutation } from "@tanstack/react-query";

export interface Props {
    contact?: Contact
}

export default function ContactForm({ contact }: Props) {

    const createContact = useCreateContact();
    const [name, setName] = useState(contact?.name || "");

    function onSubmit(e: FormEvent) {
        e.preventDefault();
        createContact.mutate({ name: "asd" });
    }

    return (
        <form onSubmit={onSubmit}>
            <div>
                <label>Name: </label>
                <input type="text" placeholder="Name"
                    value={name} onChange={e => setName(e.target.value)}/>
            </div>
        </form>
    )
}