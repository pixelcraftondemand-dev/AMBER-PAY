// Honest placeholder for journeys that are spec'd in docs/ux-flows.md but not
// built yet. Per checklist §73 we never fake financial functionality: an
// unimplemented screen says so, rather than pretending to work.

export function Placeholder({ title }: { title: string }) {
  return (
    <section aria-label={title}>
      <h1>{title}</h1>
      <p>
        This journey is specified in <code>docs/ux-flows.md</code> but is not built yet. Nothing
        here is simulated — when this screen ships, it will talk to the real Core API.
      </p>
    </section>
  );
}