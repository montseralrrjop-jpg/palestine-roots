# مشروع Android — تعليمات الرفع إلى GitHub

خطوات سريعة لرفع المشروع إلى GitHub بعد تثبيت Git على جهازك:

1. ثبت Git من: https://git-scm.com/download/win
2. افتح PowerShell في مجلد المشروع:

```powershell
cd "c:\Users\monts\Documents\trae_projects\1"
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/USERNAME/REPO.git
git push -u origin main
```

ملاحظات أمان مهمة:
- لا تشارك توكن الوصول الشخصي (PAT) في محادثات عامة. إذا نُشر توكن سابقاً، قم فوراً بإلغائه من GitHub → Settings → Developer settings → Personal access tokens.
- لا تضع التوكن في رابط `remote`؛ استخدم مصادقة آمنة أو `gh auth login`.

إذا تريد، أستطيع:
- إنشاء الريبو على GitHub نيابةً عنك إذا زودتني بتوكن جديد بصورة آمنة، أو
- إرشادك خطوة-بـخطوة عبر الـ PowerShell لإتمام العملية محلياً.
# جذور فلسطين (Palestine Roots) 🇵🇸

تطبيق "جذور فلسطين" هو منصة رقمية متطورة تهدف إلى توثيق وحماية التراث المعماري والتاريخي الفلسطيني. يتيح التطبيق للمستخدمين استكشاف المواقع الأثرية، المساجد، الكنائس، والبيوت القديمة عبر تجربة تفاعلية حديثة.

## ✨ المميزات الرئيسية
- **خريطة تفاعلية:** تجميع ذكي للمواقع (Marker Clustering) لتحسين الأداء عند عرض مئات المعالم.
- **استكشاف متطور:** البحث والفلترة حسب المحافظات والتصنيفات (ديني، تاريخي، سياحي).
- **دعم الوصول الشامل (Accessibility):** تصميم متوافق مع معايير Google العالمية لضمان سهولة الاستخدام للجميع.
- **الوضع الليلي:** دعم كامل للثيم الداكن لحماية العين وتوفير الطاقة.
- **المفضلة:** إمكانية حفظ المواقع للوصول إليها لاحقاً دون اتصال.

## 🏗️ المعمارية التقنية (Architecture)
يتبع المشروع نمط **Clean Architecture** لضمان قابلية الاختبار (Testability) والصيانة:
- **Data Layer:** التعامل مع Firebase (كمصدر خارجي) و Room (لقاعدة البيانات المحلية) و DataStore.
- **Domain Layer:** تحتوي على الـ Use Cases و Model و Repository Interface (تمثل Logic التطبيق).
- **Presentation Layer:** مبنية بالكامل باستخدام **Jetpack Compose** مع نمط **MVVM**.

## 🛠️ التقنيات المستخدمة
- **Jetpack Compose:** لبناء واجهات مستخدم برمجية حديثة.
- **Coroutines & Flow:** لإدارة العمليات الخلفية وتدفق البيانات.
- **Coil:** لتحميل الصور بكفاءة عالية مع دعم الـ Caching.
- **Google Maps Compose Utils:** لإدارة الـ Clustering على الخريطة.
- **Room Database:** للتخزين المحلي.
- **MockK & Turbine:** لاختبارات الوحدة (Unit Testing).

## 🚀 كيفية التشغيل
1. قم بعمل Clone للمستودع.
2. افتح المشروع باستخدام Android Studio (Koala أو أحدث).
3. تأكد من إعداد ملف `google-services.json` في مجلد `app/`.
4. قم ببناء المشروع وتشغيله على محاكي أو جهاز حقيقي.

---
تم التطوير بكل حب لدعم الهوية الوطنية الفلسطينية ❤️
