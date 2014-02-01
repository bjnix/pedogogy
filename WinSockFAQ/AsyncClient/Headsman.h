/***********************************************************************
 Headsman.h - A class used for destroying Singletons when the program
	goes away.  We can't use auto_ptr for this purpose because you
	can't reassign the pointer auto_ptr holds as the program runs --
	it's only meant for stack-based objects and one-time-initted static
	objects.

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
***********************************************************************/

#if !defined(HEADSMAN_H)
#define HEADSMAN_H

template <class T>
class Headsman {
public:
	Headsman(T* p = 0) : pHeld_(p) { }
	~Headsman() { delete pHeld_; }
	T* Get() { return pHeld_; }
	void Set(T* p)
	{
		if (p != pHeld_) {
			delete pHeld_;
			pHeld_ = p;
		}
	}

private:
	Headsman(const Headsman&) { }
	Headsman& operator=(const Headsman&) { return *this; }

	T* pHeld_;
};

#endif // !defined(HEADSMAN_H)

