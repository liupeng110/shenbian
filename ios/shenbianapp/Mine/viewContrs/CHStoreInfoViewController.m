//
//  CHSotoreInfoViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/2.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHStoreInfoViewController.h"
#import <IQTextView.h>
@interface CHStoreInfoViewController ()<UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong)UITableView *storeInfoTableView;
@property(nonatomic,strong)UILabel *tipsLabelHeader;

@property(nonatomic,strong)NSArray *titileList;

@end

@implementation CHStoreInfoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title =@"编辑商店信息";
    self.navigationController.navigationBar.barStyle = UIBarStyleBlackOpaque;
//    self.navigationController.navigationBar.barTintColor = [UIColor colorWithHexString:@"#404040"];

    [self.view addSubview:self.storeInfoTableView];
    [self.storeInfoTableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
        make.bottom.equalTo(self.view).offset(-49);
    }];
    
    self.titileList = @[@[@"商店头像",@"商店名称",@"商店标签",@"商店类别",@"商店简介"],@[@""],@[@"商店地址",@"联系电话",@"收款账户"]];
    
    [self.rightButton setTitle:@"保存" forState:(UIControlStateNormal)];
    self.rightButton.hidden = NO;
}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(UITableView *)storeInfoTableView{

    if (_storeInfoTableView == nil) {
        _storeInfoTableView = [[UITableView alloc]initWithFrame:(CGRectZero) style:(UITableViewStyleGrouped)];
        _storeInfoTableView.delegate = self;
        _storeInfoTableView.dataSource = self;
        [_storeInfoTableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"storeInfoCell"];
        _storeInfoTableView.tableHeaderView = self.tipsLabelHeader;
        _storeInfoTableView.sectionFooterHeight = 5;
        _storeInfoTableView.sectionHeaderHeight = 5;
        _storeInfoTableView.backgroundColor = [UIColor colorWithHexString:@"#f6f6f6"];
    }
    return  _storeInfoTableView;
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return self.titileList.count;
}


-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

    return [self.titileList[section] count];
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{

    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"storeInfoCell" forIndexPath:indexPath];
    cell.textLabel.text = self.titileList[indexPath.section][indexPath.row];
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
    cell.textLabel.font = [UIFont systemFontOfSize:15];
    cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    if (indexPath.section == 1) {
        IQTextView *textView = [[IQTextView alloc]init];
        textView.placeholder = @"简介最多300字";
        textView.frame = CGRectMake(15, 0, kScreenWidth - 30, 90);
        textView.font = [UIFont systemFontOfSize:15];
        [cell addSubview:textView];
    }
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 1 ) {
        return 90;
    }
    return 46;
}

-(UILabel *)tipsLabelHeader{

    if (_tipsLabelHeader == nil) {
        _tipsLabelHeader = [UILabel new];
        _tipsLabelHeader.text = @"  发布后，商店信息可在“我的-资料设置”中修改。";
        _tipsLabelHeader.textColor = [UIColor colorWithHexString:@"#999999"];
        _tipsLabelHeader.font = [UIFont systemFontOfSize:15];
        _tipsLabelHeader.backgroundColor = [UIColor colorWithHexString:@"#3f3f3f"];
        _tipsLabelHeader.frame = CGRectMake(0, 0, kScreenWidth, 50);

    }
    return _tipsLabelHeader;
}


-(UIStatusBarStyle)preferredStatusBarStyle{
  return   UIStatusBarStyleLightContent;
}

-(void)clickRightButton{
    
}
@end
