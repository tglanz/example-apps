import { useRouteError } from "react-router-dom";

export default function ErrorPage() {
  const error: any = useRouteError();
  console.error(error);

  return (
    <div id="error-page">
      <h1>Oops! We got a {error.status}.</h1>
      <p>
        Message: <i>{error.statusText || error.message}</i>
      </p>
      { error.data && <p>{error.data}</p> }
    </div>
  );
}