

	import java.util.Vector;
	public abstract class HW3 {
	protected int courseNumber;
	protected Vector<Integer> prereqs;
	public abstract void scheduleCourse();
	public abstract void dropCourse();
	}
	interface GradCourse {
	public abstract void setPrereqsNull();
	}
	class FirstYearGradCourse extends HW3 implements GradCourse {
	public void scheduleCourse() {}
	public void dropCourse() {}
	public void setPrereqsNull() {}
	public void okFromGradAdvisor() {}
	}
	class Dufus {
	public void help(HW3 course) {
	course.okFromGradAdvisor(); /* Number 1 */
	}
	public static void main(String[] args) {
	GradCourse cs5331 = new FirstYearGradCourse(); /* Number 2 */
	GradCourse cs5131 = new GradCourse(); /* Number 3 */
	FirstYearGradCourse cs5711 = new FirstYearGradCourse(); /* Number 4 */
	HW3 cs3311 = new HW3(); /* Number 5 */
	cs5331.scheduleCourse(); /* Number 6 */
	cs5331.setPrereqsNull(); /* Number 7 */
	cs5711.scheduleCourse(); /* Number 8 */
	cs5711.setPrereqsNull(); /* Number 9 */
	Dufus duh = new Dufus();
	duh.help(cs5331); /* Number 10 */
	}
	}

