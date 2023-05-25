import { Outlet, Link, useLoaderData, Form } from "react-router-dom";
import { Contact, getContacts } from "../services/contacts";

export interface LoaderData {
  contacts: Contact[],
}

export const loader = async (): Promise<LoaderData> => ({
  contacts: await getContacts()
});

export default function Root() {
  const { contacts } = useLoaderData() as LoaderData;

  return (
    <>
      <div id="sidebar">
        <h1>React Router Contacts</h1>
        <div>
          <Form id="search-form" role="search">
            <input
              id="q"
              aria-label="Search contacts"
              placeholder="Search"
              type="search"
              name="q"
            />
            <div
              id="search-spinner"
              aria-hidden
              hidden={true}
            />
            <div
              className="sr-only"
              aria-live="polite"
            ></div>
          </Form>
          <Form method="post">
            <button type="submit">New</button>
          </Form>
        </div>
        <nav>
          <ul>
          { contacts.map(contact => (
            <li key={contact.id}>
              <Link to={`contacts/${contact.id}`}>
                <p>{contact.first} {contact.last}</p>
              </Link>
            </li>
          ))}
          </ul>
        </nav>
      </div>
      <div id="detail">
        <Outlet />
      </div>
    </>
  );
}