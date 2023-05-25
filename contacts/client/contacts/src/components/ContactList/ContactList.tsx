import { useContacts } from "../../services/api";

export default function ContactList() {
    const { isSuccess, isLoading, error, data } = useContacts();

    if (error instanceof Error) {
        return <p>Error: {error.message}</p>;
    }

    if (isLoading) {
        return <p>Loading Contacts</p>;
    }

    if (isSuccess) {
        return (
            <div>
                {data.map(contact => (
                    <div key={contact.id}>
                        {contact.name}
                    </div>
                ))}
            </div>
        )
    }

    return <></>;
}