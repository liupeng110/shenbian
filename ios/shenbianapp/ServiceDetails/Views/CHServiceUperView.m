//
//  CHServiceMiddleView.m
//  shenbianapp
//
//  Created by book on 2017/9/14.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHServiceTableViewCell.h"
#import "CHServiceUperView.h"

@interface CHServiceUperView ()<UITableViewDataSource,UITableViewDelegate,UIScrollViewDelegate>

@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,strong)UIPageControl *pageControl;
@end

@implementation CHServiceUperView

-(instancetype)initWithFrame:(CGRect)frame{
    
    if ([super initWithFrame:frame]) {
        [self addSubview:self.tableView];
        [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        self.pageControl = [UIPageControl new];
        self.pageControl.numberOfPages = 5;
        self.pageControl.center = CGPointMake(0, 200);
        self.pageControl.backgroundColor = [UIColor orangeColor];
        [self addSubview:self.pageControl];
        self.backgroundColor = [UIColor orangeColor];
        
    }
    return self;
}

-(UITableView *)tableView{
    if (_tableView == nil) {
        
        _tableView = [[UITableView alloc]initWithFrame:(CGRectZero) style:(UITableViewStyleGrouped)];
        [_tableView registerClass:[CHServiceTableViewCell class] forCellReuseIdentifier:@"serviceCell"];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.separatorColor = [UIColor colorWithHexString:@"#ebebeb"];
        _tableView.backgroundColor = [UIColor colorWithHexString:@"f6f6f6"];
        _tableView.estimatedRowHeight = 0;
        _tableView.estimatedSectionFooterHeight = 0;
        _tableView.estimatedSectionHeaderHeight = 0;

        if (@available(iOS 11.0, *)) {
            _tableView.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentNever;
        } else {
            // Fallback on earlier versions
        }
    }
    return _tableView;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    
    return 3;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    switch (section) {
        case 0:{
            return 1;
        }
            break;
        case 1:
        {
            return 2;
        }
            break;
        case 2:{
            return 1;
        }
            break;
            
        default:
            break;
    }
    return 5;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHServiceTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"serviceCell" forIndexPath:indexPath];
    cell.indexPath = indexPath;
    CHServiceCellModel *model = [CHServiceCellModel new];
    
    cell.cellModel = model;
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    switch (indexPath.section) {
        case 0:{
            
            return 150;
        }
            break;
        case 1:{
            return 55;
        }
            break;
        case 2:{
            return 150;
        }
            break;
            
        default:
            break;
    }
    
    return 44;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    switch (section) {
        case 0:
        {
            return 220;
        }
        case 1:{
            
            return 55;
        }
        case 2:{
            return 0.01;
        }
            break;
            
        default:
            break;
    }
    return 0.01;
    
}

-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.01;
}

-(UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    
    if (section == 0) {
        UIScrollView *scrollView = [[UIScrollView alloc]initWithFrame:(CGRectMake(0, 0, kScreenWidth, 220))];
        for (int i = 0; i < 5; i++) {
            UIImageView *imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_image"]];
            imageView.frame = CGRectMake( kScreenWidth * i,0, kScreenWidth, 220);
            [scrollView addSubview:imageView];
        }
        scrollView.delegate = self;
        scrollView.pagingEnabled = YES;
        scrollView.contentSize = CGSizeMake(kScreenWidth * 5, 220);
        
        return scrollView;
        
    } else if (section == 1){
        UIView *contentView = [[UIView alloc]initWithFrame:(CGRectMake(0, 0, kScreenWidth, 55))];
        contentView.backgroundColor = [UIColor colorWithHexString:@"#f6f6f6"];
        UILabel* _priceLabel = [UILabel new];
        _priceLabel.text = @"￥100";
        _priceLabel.font = [UIFont systemFontOfSize:22];
        _priceLabel.textColor = [UIColor colorWithHexString:@"#009698"];
        [contentView addSubview:_priceLabel];
        [_priceLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(contentView).offset(15);
            make.centerY.equalTo(contentView);
            
        }];
        UIButton* _shopCartButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_shopCartButton setTitle:@"加入购物车" forState:(UIControlStateNormal)];
        [_shopCartButton setTitleColor:[UIColor colorWithHexString:@"#009698"] forState:(UIControlStateNormal)];
        _shopCartButton.titleLabel.font = [UIFont systemFontOfSize:12];
        
        [_shopCartButton setImage:[UIImage imageNamed:@"spxq_gwc"] forState:(UIControlStateNormal)];
        _shopCartButton.titleEdgeInsets = UIEdgeInsetsMake(0, -40, -50, 0);
        @weakify(self);
        _shopCartButton.rac_command = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            @strongify(self);
            if (self.clickAddShopCart) {
                self.clickAddShopCart();
            }
            return [RACSignal empty];
        }];
        [contentView addSubview:_shopCartButton];
        [_shopCartButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.equalTo(contentView).offset(-10);
            make.right.equalTo(contentView).offset(-15);
            make.width.mas_equalTo(60);
            make.height.mas_equalTo(40);
        }];
        return contentView;
    }
    
    return nil;
}

-(void)scrollViewDidScroll:(UIScrollView *)scrollView{
    int page = scrollView.contentOffset.x / scrollView.frame.size.width;
    self.pageControl.currentPage = page;
}

@end
