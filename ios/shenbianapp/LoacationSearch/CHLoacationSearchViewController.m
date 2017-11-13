//
//  CHLoacationSearchViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/10.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHLoacationSearchViewController.h"

@interface CHLoacationSearchViewController ()<UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,strong)NSArray *locationList;
@end

@implementation CHLoacationSearchViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.rightButton setTitle:@"确认" forState:(UIControlStateNormal)];
    self.rightButton.hidden = NO;
    
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.tabBarController.tabBar.hidden = NO;
    
}

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:0 inSection:0];
    UITableViewCell *cell = [self.tableView cellForRowAtIndexPath:indexPath];
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#009698"];

}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)bindViewControllerModel{
    self.locationList = @[@"北京，海淀区",@"北京，海淀区，五道口",@"北京，海淀区，上地"];
}

-(void)setupViews{
 
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
        make.bottom.equalTo(self.view).offset(0);
    }];
}

- (UITableView *)tableView{

    if (_tableView == nil) {
        _tableView = [UITableView new];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"locationCell"];
        _tableView.tableFooterView = [UIView new];
        _tableView.separatorColor = [UIColor colorWithHexString:@"#ebebeb"];
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.locationList.count;
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"locationCell"];
    cell.textLabel.text = self.locationList[indexPath.row];
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#2d333a;"];
    cell.textLabel.font = [UIFont systemFontOfSize:15];
    cell.textLabel.textAlignment = NSTextAlignmentCenter;
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    return cell;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    for (UILabel *label in tableView.visibleCells) {
        label.textColor = [UIColor colorWithHexString:@"#2d333a;"];
    }
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#009698"];

}

-(void)clickRightButton{
    [self.navigationController popViewControllerAnimated:YES];
}

@end
