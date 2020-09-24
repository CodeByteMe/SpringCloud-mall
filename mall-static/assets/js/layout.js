Vue.component("leftnav",{
	template:`<div id="leftnav">
				<el-row class="tac">
				  <el-col :span="12" style="width: 100%;">
				    <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen"
				      @close="handleClose" background-color="#304156" text-color="#fff" active-text-color="#ffd04b">
						<template>
							<el-submenu v-for="(item,index) in menu" :key="item.id" v-if="item.level == 0" :index="item.id">
								<template slot="title">
									<i :class="item.icon"></i>
									<span>{{item.title}}</span>
								</template>
								<el-menu-item-group v-for="(item1,index1) in item.children" :key="item1.id">
									<el-menu-item :index="item1.id"><el-link type="primary" :href="item1.url" target="main_self_frame">{{item1.title}}</el-link></el-menu-item>
								</el-menu-item-group>
							</el-submenu>
						</template>
				    </el-menu>
				  </el-col>
				</el-row>
			</div>`,
	props:["menu"],
	methods:{
		handleOpen:function(key, keyPath){
			console.log("handleOpen");
			console.log(key, keyPath);
		},
		handleClose:function(key, keyPath){
			console.log("handleOpen");
			console.log(key, keyPath);
		}
	}
});