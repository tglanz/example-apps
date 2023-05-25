import { Form } from "react-router-dom";
import { Contact } from '../services/contacts';


export default function Contact() {
    const contact: Contact = {
        id: 1,
        first: "Your",
        last: "Name",
        avatar: "https://www.gravatar.com/avatar/8f38e71a3538e8f8f4912bc1b4526518",
    }

    return (
        <div id="contact">
            <div>
                <img key={contact.avatar} src={contact.avatar} />
            </div>

            <div>
                <h1>{contact.first} {contact.last}</h1>
            </div>
        </div>
    )
}