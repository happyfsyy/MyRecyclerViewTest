# MyRecyclerViewTest
---
### NormalRecyclerViewActivity：
---
* 实现了基本的RecyclerView，加入了OnItemClickListener和OnClickListener(点击图片)，又加入了不同的ViewType，测试了adapter.notify。
* 为了测试RecyclerView的回收利用机制，先给onCreateViewHolder()加log，看执行了几次，然后在Adapter中加入了normalViewNum和headerViewNum，又在Activity中根据view.getTag，判断当前的itemView是不是新建的。

### MultiLayoutActivity:
---
* 实现了横向滑动的RecyclerView和StaggerGrid，为RecyclerView加入了divider。
* 简单封装了ItemDecoration，根据ListDecoration构造函数中传入的orientation来判断实现不同方向的divider。

### HeaderActivity
---
* 实现给RecyclerView添加HeaderView

### BaseAdapterActivity
---
* BaseRecyclerAdapter封装了一部分通用Adapter的功能，任何RecyclerView，，写Adapter的时候只需要继承BaseRecyclerAdapter实现自己的功能就好。
* 还实现了对addHeaderView这个功能的封装，支持各种LayoutManager，支持HeaderView内部的Click事件。
*