//
//  CHServiceMiddleView.m
//  shenbianapp
//
//  Created by book on 2017/9/14.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHServiceMiddleView.h"
#import "CHServiceTableViewCell.h"
@interface CHServiceMiddleView ()<UITableViewDataSource,UITableViewDelegate>

@property(nonatomic,strong)UITableView *tableView;
@end

@implementation CHServiceMiddleView

-(instancetype)initWithFrame:(CGRect)frame{
    
    if ([super initWithFrame:frame]) {
        [self addSubview:self.tableView];
        [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        
        
    }
    return self;
}

-(UITableView *)tableView{
    if (_tableView == nil) {
        
        _tableView = [UITableView new];
        [_tableView registerClass:[CHServiceTableViewCell class] forCellReuseIdentifier:@"serviceCell"];
        _tableView.delegate = self;
        _tableView.dataSource = self;
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
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
    switch (indexPath.row) {
        case 0:
            return 200;
            break;
        case 1:
            return 55;
            break;
        case 2:
            return 55;
            break;
        case 3:
            return 50;
            break;
            
        case 4:
            return 150;
            break;
        
        default:
            break;
    }
    return 44;
}
@end
