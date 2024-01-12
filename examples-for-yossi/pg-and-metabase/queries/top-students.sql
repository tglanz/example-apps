with s1 as (
  select semester, course, max(grade) as grade
  from students
  group by (semester, course)
)
select s1.semester, s1.course, s1.grade, name
from s1
left join students on 
  s1.semester = students.semester and
  s1.course = students.course and
  s1.grade = students.grade;
