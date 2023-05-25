import { Link, Outlet } from "react-router-dom";
import ContactList from "../../components/ContactList/ContactList";

import styles from "./Home.module.css";
import ContactForm from "../../components/ContactForm/ContactForm";

export default function HomeRoute() {
    return (
        <div className={styles.container}>
            <div className={styles.toolbox}>
                <Link to={"/contacts/new"}>New Contact</Link>
            </div>

            <div className={styles.contacts}>
                <h3>Contacts</h3>
                <ContactList />
            </div>

            <div className={styles.create}>
                <ContactForm />
            </div>
        </div>
    )
}