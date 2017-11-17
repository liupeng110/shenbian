//
//  CHMessageViewController.m
//  shenbianapp
//
//  Created by book on 2017/10/17.
//  Copyright © 2017年 . All rights reserved.
//

#import "CHMessageViewController.h"
#import "CHMessageTableViewCell.h"
#import "CHChatRoomViewController.h"

#import "CHFocusViewController.h"
#import "CHMyOrdersViewController.h"
@interface CHMessageViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong)UIView *upperView;
@property(nonatomic,strong)UIView* lowerView;
@property(nonatomic,strong)UIScrollView *scrollView;
@property(nonatomic,strong)UIPageControl *pageControl;

@property(nonatomic,strong)UITableView *tableView;

@end

@implementation CHMessageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.navBarView.mhBaseTitleLabel.text = @"消息";
    self.navigationController.navigationBar.tintColor = [UIColor colorWithHexColor:@"#008E8F"];
    [self.view addSubview:self.upperView];
    [self.upperView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.view).offset(64);
        make.left.right.equalTo(self.view);
        make.height.mas_equalTo(200);
    }];
    
    [self.upperView addSubview:self.pageControl];
    [self.pageControl mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.equalTo(self.upperView);
        make.bottom.equalTo(self.upperView.mas_bottom).offset(-10);
    }];
    
    UIImageView *imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
    imageView.frame = CGRectMake(0, 0, kScreenWidth, 200) ;
    
    [self.scrollView addSubview:imageView];
    
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.upperView.mas_bottom);
        make.bottom.equalTo(self.view).offset(-49);
    }];
    
    NSMutableArray *tempArr = [NSMutableArray array];
    for (int i = 0; i < 3; i++) {
        CHMessageModel *model = [[CHMessageModel alloc]init];
        model.headUrl = @"http://n.sinaimg.cn/default/8_img/uplaod/3933d981/20171022/rq-Y-fymzzpv8912460.jpg";
        model.userName = @"我是关关";
        model.briefMessage = @"简要消息";
        model.lastTime = [NSString stringWithFormat:@"昨天 %d：%d",13 + i,20 - i];
        model.messageType = i;
        [tempArr addObject:model];
    }
    self.messageModelList = tempArr;
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = NO;
    self.navigationController.navigationBarHidden = YES;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



-(UIView *)upperView{

    if (_upperView == nil) {
        _upperView = [[UIView alloc]init];
        UIScrollView *scrollView = [[UIScrollView alloc]init];
        scrollView.showsVerticalScrollIndicator = NO;
        scrollView.showsHorizontalScrollIndicator = NO;
        scrollView.frame = CGRectMake(0, 0, kScreenWidth, 200);
        [_upperView addSubview:scrollView];
        self.scrollView = scrollView;
        
    }
    return _upperView;
}

-(UIPageControl *)pageControl{
    if (_pageControl == nil) {
        _pageControl = [[UIPageControl alloc]init];
        _pageControl.numberOfPages = 5;
        _pageControl.centerX = kScreenWidth/4;
        _pageControl.centerY = 160;
    }
    return _pageControl;
}


#pragma -mark tableview 

-(UITableView *)tableView{

    if (_tableView == nil) {
        _tableView = [[UITableView alloc]init];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[CHMessageTableViewCell class] forCellReuseIdentifier:@"messageCell"];
        _tableView.tableFooterView = [[UIView alloc]init];
    }
    
    return _tableView;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHMessageTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"messageCell"];
    cell.model = self.messageModelList[indexPath.row];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

    return self.messageModelList.count;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return 80;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    [tableView deselectRowAtIndexPath:indexPath animated:true];
    
    CHMessageModel *model = self.messageModelList[indexPath.row];
    
    switch (model.messageType) {
        case MessageTypeChat:{
            CHChatRoomViewController *chatRoom = [[CHChatRoomViewController alloc]initWithConversationType:ConversationType_PRIVATE targetId:@"1"];
            [self.navigationController pushViewController:chatRoom animated:YES];
            
        }
            
            break;
        case MessageTypeOrder:{//跳转订单详情
            CHMyOrdersViewController *myOrder = [CHMyOrdersViewController new];
            [self.navigationController pushViewController:myOrder animated:YES];
        }
            break;
            
        case MessageTypeFocus:{//跳转关注页
        
            CHFocusViewController *focus = [CHFocusViewController new];
            [self.navigationController pushViewController:focus animated:YES];
        }
            
            break;
        default:
            break;
    }
    }






@end
