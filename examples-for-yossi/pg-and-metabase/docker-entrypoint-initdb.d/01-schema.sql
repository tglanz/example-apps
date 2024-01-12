CREATE TABLE students (
  id serial,
  name text,
  grade numeric,
  semester text,
  course text,
  PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS students_semester_idx ON students USING btree (semester);
