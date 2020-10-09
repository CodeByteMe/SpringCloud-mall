Vue.component("rightnavtop",{
	template:`<div id="rightnav-top">
					<el-card shadow="hover">
						<el-breadcrumb separator="/" style="float: left;">
							<el-breadcrumb-item><a href="home.html">首页</a></el-breadcrumb-item>
							<el-breadcrumb-item>管理</el-breadcrumb-item>
							
						</el-breadcrumb>
						<el-dropdown trigger="click" style="margin-left: 76%;">
							<span class="el-dropdown-link">
								{{title}}<i class="el-icon-arrow-down el-icon--right"></i>
							</span>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item icon="el-icon-s-unfold">首页</el-dropdown-item>
								<el-dropdown-item icon="el-icon-s-promotion">退出</el-dropdown-item>
							</el-dropdown-menu>
						</el-dropdown>
					</el-card>
				</div>`,
	props:["title"]
});