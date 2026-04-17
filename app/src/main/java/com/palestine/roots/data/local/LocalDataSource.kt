package com.palestine.roots.data.local

import com.palestine.roots.data.model.Site

object LocalDataSource {
    
    // بيانات المواقع الأثرية - مدمجة في التطبيق
    val allSites = listOf(
        // القدس
        Site(
            id = "1",
            nameAr = "المسجد الأقصى",
            city = "القدس",
            description = "أولى القبلتين وثالث الحرمين الشريفين، مساحته 144 دونم، يضم قبة الصخرة والمصلى القبلي.",
            imageUrl = "", // نضع اسم الصورة من drawable لاحقاً
            latitude = 31.7780,
            longitude = 35.2354,
            history = "بُني أول مرة في عهد آدم عليه السلام، ثم أعيد بناؤه عبر العصور..."
        ),
        Site(
            id = "2",
            nameAr = "كنيسة القيامة",
            city = "القدس",
            description = "أقدس كنيسة في العالم المسيحي، بُنيت فوق موقع صلب المسيح ودفنه وقيامته.",
            imageUrl = "",
            latitude = 31.7784,
            longitude = 35.2294,
            history = "أمر ببنائها الإمبراطور قسطنطين عام 335م..."
        ),
        
        // الخليل
        Site(
            id = "3",
            nameAr = "الحرم الإبراهيمي",
            city = "الخليل",
            description = "مقام الأنبياء إبراهيم، إسحاق، يعقوب وزوجاتهم. تحفة معمارية هيرودية.",
            imageUrl = "",
            latitude = 31.5256,
            longitude = 35.1106,
            history = "بناه هيرودس الكبير قبل 2000 عام، وهو واحد من أقدم المباني الدينية في العالم..."
        ),
        
        // نابلس
        Site(
            id = "4",
            nameAr = "بئر يعقوب",
            city = "نابلس",
            description = "بئر تاريخي ذكر في التوراة، حفره النبي يعقوب عليه السلام منذ أكثر من 3800 عام.",
            imageUrl = "",
            latitude = 32.2115,
            longitude = 35.2875,
            history = "يقع في بلدة بلاطة شرق نابلس، ولا يزال البئر موجوداً حتى اليوم..."
        ),
        
        // رام الله
        Site(
            id = "5",
            nameAr = "قصر رام الله الثقافي",
            city = "رام الله",
            description = "مبنى تراثي عثماني تحول لمركز ثقافي وفني، يعكس تاريخ المدينة الحديث.",
            imageUrl = "",
            latitude = 31.9038,
            longitude = 35.2034,
            history = "بُني في أواخر العهد العثماني، واستخدم كمقر للحكم المحلي..."
        ),
        
        // أريحا
        Site(
            id = "6",
            nameAr = "تل السلطان - أريحا القديمة",
            city = "أريحا",
            description = "أقدم مدينة مأهولة في العالم، تعود آثارها لأكثر من 10,000 سنة قبل الميلاد.",
            imageUrl = "",
            latitude = 31.8706,
            longitude = 35.4444,
            history = "اكتشفت فيها أقدم سور في التاريخ، وأبراج حجرية تعود للعصر الحجري..."
        )
    )
    
    // دوال الجلب المحلي
    fun getSitesByCity(city: String): List<Site> {
        return allSites.filter { it.city == city }
    }
    
    fun getSiteById(id: String): Site? {
        return allSites.find { it.id == id }
    }
}
