import './index.css'

import Root, {loader as rootLoader} from './routes/Root'
import Contact from './routes/Contact';
import ErrorPage from './ErrorPage';

import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        errorElement: <ErrorPage />,
        loader: rootLoader,
        children: [
            {
                path: "contacts/:contactId",
                element: <Contact />,
            }
        ]
    },
])

export default function Router() {
    return (
        <RouterProvider router={router} />
    );
}
