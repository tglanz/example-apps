#

## Monorepos

Contain mutiple apps and libraries within the same repository

### The good parts

**Easier onboarding.** Navigating a single codebase is easier than multiple (Up to a point).

**Code sharing.** By maintaining the code inside the same repository sharing code is easy

- Modifications are reflected immediately
- No need for external package repositories
- No need for complex versioning schemes

**CI/CD.** The code is contained as a whole within the SCM, therefore it is easier to apply CI/CD pipelines. For example, deploy application on release, apply e2e on library changes etc...

**Great visibility.** Having everything in a single location makes everything approachable.

### The bad parts

**CI overload.** Because the repository contains multiple applications and libraries, the CI will trigger often.

**IDE.** When navigating a big codebase, your IDE can suffer.

**Access control.** Granular access control is basically non-existent.

## CI/CD

### CI

- Code conventions 
- Tests
- Vulnerabilities

### CD

- Artifacts
- Deployments

## Component styling

**CSS.** Pretty bad usually - there is no scope (or shadowing) and it gets out of control very fast.

**CSS Modules.** Scoped CSS. CSS is defined external to the component.

**Styled Components.** A method to wrap component with a style, specified in code. CSS is defined near the component.

**Tailwind CSS.** CSS framework (and a mindset) that provides classes. You style the componets by chaining many classes.
